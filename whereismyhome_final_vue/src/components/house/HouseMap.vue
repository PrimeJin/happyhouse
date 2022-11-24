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
  </div>
</template>

<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseMap",
  components: {
    HouseSearchBar,
    HouseList,
  },
  data() {
    return {
      latitude: 33.450701,
      longitude: 126.570667,
      positions: [],
      markers: [],
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
        script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
        document.head.appendChild(script);
      }
    } else {
      this.latitude = 33.450701;
      this.longitude = 126.570667;
      this.initMap();
    }
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  watch: {
    houses() {
      console.log("watch");
      this.displayMarker();
    },
  },
  methods: {
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
    },
    displayMarker() {
      // 데이터 초기화
      this.positions = [];
      this.markers = [];

      // 현재 위치의 매물 위치 정보
      if (0 < this.houses.length) {
        // data의 위도 경도 저장
        for (let i = 0; i < this.houses.length; i++) {
          var markerPosition = new kakao.maps.LatLng(this.houses[i].lat, this.houses[i].lng);
          this.positions.push(markerPosition);
        }

        var bounds = new kakao.maps.LatLngBounds();
        // 마커 이미지
        var marker;
        var imageSrc = require("@/assets/house.png"),
          imageSize = new kakao.maps.Size(50, 50),
          imageOption = { offset: new kakao.maps.Point(0, 0) };
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
        for (let i = 0; i < this.positions.length; i++) {
          // 마커 추가
          marker = new kakao.maps.Marker({
            position: this.positions[i],
            image: markerImage,
            clickable: true,
          });
          marker.setMap(this.map);
          this.markers.push(marker);
          // 인포윈도우
          var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="padding:5px;">' + this.houses[i].aptName + "</div>",
            removable: true,
          });
          // 이벤트 등록
          kakao.maps.event.addListener(marker, "click", this.selectMarkerHouse(this.map, marker, infowindow));

          bounds.extend(this.positions[i]);
        }
        // 지도 범위 조정
        this.map.setBounds(bounds);
        // 클러스터리 추가
      }
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
}

.container {
  position: absolute;
  top: 0%;
  left: 0%;
  padding: 0;
  margin: 0;
  width: 20%;
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
