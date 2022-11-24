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
      Mmarkers: [],
      Pmarkers: [],
      Smarkers: [],
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
    ...mapState(houseStore, ["houses", "seoulHouse", "infraScore", "MScore", "PScore", "SScore"]),
  },
  watch: {
    houses() {
      console.log("watch");
      this.centerSet();
    },
  },
  methods: {
    ...mapActions(houseStore, ["getSeoluList", "setMScore", "setPScore", "setSScore", "setInfraScore"]),

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

        // 대형마트
        this.ps.categorySearch("MT1", this.placesSearchCM, { useMapBounds: true });
        // 어린이집, 유치원
        this.ps.categorySearch("PS3", this.placesSearchCP, { useMapBounds: true });
        // 지하철역
        this.ps.categorySearch("SW8", this.placesSearchCS, { useMapBounds: true });
      }
    },
    placesSearchCM(data, status) {
      var imageSrc = require("@/assets/store.png"),
        imageSize = new kakao.maps.Size(30, 30),
        imageOption = { offset: new kakao.maps.Point(0, 0) };
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          var marker = new kakao.maps.Marker({
            map: this.map,
            image: markerImage,
            position: new kakao.maps.LatLng(data[i].y, data[i].x),
          });
          this.Mmarkers.push(marker);
        }
      }
      // this.MScore = this.Mmarkers.length;
      if (this.Mmarkers.length > 0) this.setMScore(this.Mmarkers.length);
    },
    placesSearchCP(data, status) {
      var imageSrc = require("@/assets/school.png"),
        imageSize = new kakao.maps.Size(30, 30),
        imageOption = { offset: new kakao.maps.Point(0, 0) };
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          var marker = new kakao.maps.Marker({
            map: this.map,
            image: markerImage,
            position: new kakao.maps.LatLng(data[i].y, data[i].x),
          });
          this.Pmarkers.push(marker);
        }
        // this.PScore = this.Pmarkers.length;
        if (this.Pmarkers.length > 0) this.setPScore(this.Pmarkers.length);
      }
    },
    placesSearchCS(data, status) {
      var imageSrc = require("@/assets/subway.png"),
        imageSize = new kakao.maps.Size(30, 30),
        imageOption = { offset: new kakao.maps.Point(0, 0) };
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
      if (status === kakao.maps.services.Status.OK) {
        for (var i = 0; i < data.length; i++) {
          var marker = new kakao.maps.Marker({
            map: this.map,
            image: markerImage,
            position: new kakao.maps.LatLng(data[i].y, data[i].x),
          });
          this.Smarkers.push(marker);
        }
      }
      // this.SScore = this.Smarkers.length;
      if (this.Smarkers.length > 0) this.setSScore(this.Smarkers.length);
      // console.log("개수 합산");
      // console.log(this.SScore);
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
  border-radius: 2px;
  background-color: #FCFAF1;
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
