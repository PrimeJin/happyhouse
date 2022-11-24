<template>
  <div id="map">
    <div class="container">
      <nav>
        <ul class="mcd-menu">
          <li style="padding: 10px">
            <house-search-bar />
          </li>
          <li>
            <house-list />
          </li>
        </ul>
      </nav>
    </div>
    <house-detail />
  </div>
</template>

<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseMap",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
  },
  data() {
    return {
      latitude: 33.450701,
      longitude: 126.570667,
      positions: [],
      markers: [],
      clusterer: null,
      ps: null,
    };
  },
  mounted() {
    if (navigator.geolocation) {
      // 현재 위치
      navigator.geolocation.getCurrentPosition((pos) => {
        this.latitude = pos.coords.latitude;
        this.longitude = pos.coords.longitude;
      });
      if (window.kakao && window.kakao.maps) {
        this.initMap();
      } else {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=fd238029c35092754edfae10d0b0e6f9&libraries=services,clusterer,drawing";
        document.head.appendChild(script);
      }
    } else {
      this.latitude = 33.450701;
      this.longitude = 126.570667;
      this.initMap();
    }
  },
  computed: {
    ...mapState(houseStore, ["houses", "seoulHouse"]),
  },
  watch: {
    houses() {
      console.log("watch");
      this.centerSet();
    },
  },
  methods: {
    ...mapActions(houseStore, ["getSeoluList"]),

    initMap() {
      // 맵 생성
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.latitude, this.longitude),
        level: 3,
      };
      this.map = new kakao.maps.Map(container, options);
      // 스카이뷰&확대축소바
      var mapTypeControl = new kakao.maps.MapTypeControl();
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
      // 클러스터리
      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map,
        averageCenter: true,
        minLevel: 5,
      });
      // 카테고리 창
      this.ps = new kakao.maps.services.Places(this.map);
      // 마커
      this.displayMarker();
    },
    async centerSet() {
      var bounds = new kakao.maps.LatLngBounds();
      this.positions = [];
      // 현재 위치의 매물 위치 정보
      if (0 < this.houses.length) {
        // data의 위도 경도 저장
        for (let i = 0; i < this.houses.length; i++) {
          var markerPosition = new kakao.maps.LatLng(this.houses[i].lat, this.houses[i].lng);
          this.positions.push(markerPosition);
          bounds.extend(this.positions[i]);
        }
        // 지도 범위 조정
        this.map.setBounds(bounds);

        // 카테고리
        this.ps.categorySearch("BK9", this.placesSearchCB, { useMapBounds: true });
      }
    },
    placesSearchCB(data, status) {
      var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          var marker = new kakao.maps.Marker({
            map: this.map,
            position: new kakao.maps.LatLng(data[i].y, data[i].x),
          });

          // 마커에 클릭이벤트를 등록합니다
          kakao.maps.event.addListener(marker, "click", function () {
            // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
            infowindow.setContent('<div style="padding:5px;font-size:12px;">' + data[i].place_name + "</div>");
            infowindow.open(this.map, marker);
          });
        }
      }
    },
    displayMarker() {
      if (this.seoulHouse.length == 0) {
        console.log("전체 데이터 조회");
        this.getSeoluList();
      }
      // console.log("마커 생성");
      var marker;
      var imageSrc = require("@/assets/house.png"),
        imageSize = new kakao.maps.Size(50, 50),
        imageOption = { offset: new kakao.maps.Point(0, 0) };
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
      for (let i = 0; i < this.seoulHouse.length; i++) {
        // 마커 추가
        var position = new kakao.maps.LatLng(this.seoulHouse[i].lat, this.seoulHouse[i].lng);
        marker = new kakao.maps.Marker({
          position: position,
          image: markerImage,
          clickable: true,
        });
        marker.setMap(this.map);
        this.markers.push(marker);
        // 인포윈도우
        var infowindow = new kakao.maps.InfoWindow({
          content: '<div style="padding:5px;">' + this.seoulHouse[i].aptName + "</div>",
          removable: true,
        });
        // 이벤트 등록
        kakao.maps.event.addListener(marker, "click", this.selectMarkerHouse(this.map, marker, infowindow));
      }
      console.log("클러스터리");
      this.clusterer.addMarkers(this.markers);
    },
    selectMarkerHouse(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },
  },
};
</script>

<style scoped>
#map {
  position: relative;
  width: 100%;
  height: 92vh;
  padding: 0;
  margin: 0;
  z-index: 0;
}

.container {
  position: absolute;
  top: 0%;
  left: 0%;
  padding: 0;
  margin: 0;
  width: 17vw;
  z-index: 100;
  clear: both;
}
.mcd-menu {
  list-style: none;
  height: 100vh;
  padding: 0;
  margin: 0;
  background: #fff;
  border-radius: 2px;
  overflow-y: auto;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
}
.mcd-menu li {
  position: relative;
}
.mcd-menu li a {
  display: block;
  text-decoration: none;
  padding: 12px 20px;
  color: #777;

  text-align: left;
  height: 36px;
  position: relative;
  border-bottom: 1px solid #eee;
}
</style>
