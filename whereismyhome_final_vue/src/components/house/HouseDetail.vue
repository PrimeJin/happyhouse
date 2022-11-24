<template>
  <aside v-if="house">
    <button @click="closeSidebar()">
      <b-img :src="require('@/assets/cross-mark.png')" fluid-grow></b-img>
    </button>
    <b-row>
      <b-col>
        <h3>{{ house.aptName }}</h3>
      </b-col>
    </b-row>
    <b-row class="mb-2 mt-1">
      <b-col><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img></b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary">일련번호 : {{ house.aptCode }}</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="primary">아파트 이름 : {{ house.aptName }} </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info">법정동 : {{ house.dongName }} </b-alert>
      </b-col>
    </b-row>
  </aside>
</template>

<script>
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(houseStore, ["house"]),
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  methods: {
    ...mapActions(houseStore, ["resetHouse"]),
    closeSidebar() {
      // console.log("clear");
      this.resetHouse();
    },
  },
};
</script>

<style scope>
button {
  position: relative;
  float: right;
  border: 0;
  outline: 0;
  width: 3em;
  background-color: transparent;
}

aside {
  position: absolute;
  z-index: 300;
  height: 100vh;
  width: 17vw;
  left: 17vw;
  padding: 20px;
  background-color: rgb(255, 255, 255);
}
aside h4 {
  position: relative;
  display: inline;
  margin-bottom: 25px;
  font-size: 30px;
}
aside h4::after {
  content: "";
  position: absolute;
  width: 50%;
  height: 1px;
  background-color: #437eeb;
  left: 110%;
  top: 50%;
}
aside .category {
  list-style: none;
  padding: 0;
  margin: 25px 0;
}
aside .category li {
  margin-bottom: 15px;
  padding-bottom: 10px;
  font-size: 14px;
  color: #777;
  border-bottom: 1px solid #eee;
  position: relative;
}
aside .category li:last-child {
  border-bottom: none;
}
aside .category li::after {
  content: attr(data-number);
  position: absolute;
  width: 25px;
  height: 20px;
  background-color: #eee;
  right: 0;
  border-radius: 4px;
  text-align: center;
  line-height: 1.5;
  color: #000;
  font-size: 12px;
}
aside .gallery {
  display: grid;
  grid-template-rows: repeat(2, 90px);
  grid-template-columns: repeat(3, 100px);
  gap: 5px;
  margin: 25px 0;
}
aside .gallery img {
  width: 90px;
  height: 80px;
  object-fit: cover;
}
aside .archive {
  list-style: none;
  padding: 0;
}
aside .archive li {
  font-size: 12px;
  color: #777;
  margin-bottom: 15px;
  position: relative;
  padding-left: 20px;
  font-weight: bold;
}
aside .archive li::before {
  font-family: "FontAwesome";
  content: "\f07b";
  position: absolute;
  left: 0;
  top: 2px;
  font-size: 10px;
}
</style>
