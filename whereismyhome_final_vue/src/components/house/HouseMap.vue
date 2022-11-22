<template>
  <div v-show="isWatch" id="map"></div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseMap",
  data() {
    return {
      map: null,
      markers: [],
      lat: [],
      lng: [],
      mapHouses: [],
      isWatch: "false",
    };
  },
  created() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  watch: {
    houses(val) {
      console.log("watch");
      this.isWatch = true;
      this.mapHouses = val;
      this.displayMarker();
    },
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 2,
      };
      this.map = new kakao.maps.Map(container, options);
      var mapTypeControl = new kakao.maps.MapTypeControl();
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    },
    displayMarker() {
      this.lat = [];
      this.lng = [];
      this.markers = [];
      if (0 < this.mapHouses.length) {
        for (let i = 0; i < this.mapHouses.length; i++) {
          this.lat.push(this.mapHouses[i].lat);
          this.lng.push(this.mapHouses[i].lng);
        }

        for (let i = 0; i < this.mapHouses.length; i++) {
          var markerPosition = new kakao.maps.LatLng(this.lat[i], this.lng[i]);
          this.markers.push(markerPosition);
        }

        var bounds = new kakao.maps.LatLngBounds();
        // 마커 이미지
        var i, marker;
        var imageSrc = require("@/assets/building.png"),
          imageSize = new kakao.maps.Size(20, 30),
          imageOption = { offset: new kakao.maps.Point(0, 0) };
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
        for (i = 0; i < this.markers.length; i++) {
          // 마커 추가
          marker = new kakao.maps.Marker({
            position: this.markers[i],
            image: markerImage,
            clickable: true,
          });
          marker.setMap(this.map);
          // 인포윈도우
          var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="padding:5px;">' + this.mapHouses[i].aptName + "</div>",
            removable: true,
          });
          // 이벤트 등록
          kakao.maps.event.addListener(marker, "click", this.selectMarkerHouse(this.map, marker, infowindow));

          bounds.extend(this.markers[i]);
        }
        // 지도 범위 조정
        this.map.setBounds(bounds);
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
.test {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

#map {
  width: 400px;
  height: 500px;
  border: 1px #a8a8a8 solid;
}
</style>
