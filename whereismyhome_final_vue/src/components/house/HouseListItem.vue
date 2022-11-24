<template>
  <b-card
    no-body
    class="overflow-hidden"
    style="color:#5F403B; max-width: 540px; border: 0px; background-color: #FCFAF1"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-row no-gutters>
      <b-col md="4">
        <b-card-img src="https://picsum.photos/400/400/?image=20" alt="Image" class="rounded-0"></b-card-img>
      </b-col>
      <b-col md="8">
        <b-card-body @click="selectHouse">
          <strong>[{{ house.aptCode }}] {{ house.aptName }}</strong>
        </b-card-body>
      </b-col>
    </b-row>
  </b-card>
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // var latitude = this.house.lat;
      // var longitude = this.house.lng;
      // var moveLatLon = new kakao.maps.LatLng(latitude, longitude);
      // this.map.panTo(moveLatLon);

      console.log("listRow : ", this.house);
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
