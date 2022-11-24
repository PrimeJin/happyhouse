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
    <ol class="large-numbers">
      <li>{{ house.sidoName }} {{ house.gugunName }} {{ house.dongName }}</li>
      <li>매매가 {{ house.recentPrice }}원</li>
      <li>{{house.buildYear}}년 준공</li>
      <h4>인프라 {{infraScore}}점</h4>
      <li>인근 대형마트 {{MScore}}개</li>
      <li>인근 교육기관 {{PScore}}개</li>
      <li>인근 지하철역 {{SScore}}개</li>
      <div>차트</div>
    </ol>
    </b-row>
    <b-row>
      <canvas id="bar-chart" width="300" height="230"></canvas>
      <canvas id="bar-chart" width="300" height="230"></canvas>
    </b-row>
  </aside>
</template>

<script>
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(houseStore, ["house", "infraScore", "MScore", "PScore", "SScore"]),
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  watch: {
    house() {
      let cnt = this.SScore + this.MScore + this.PScore;
      let sum = 0;
      for(let i=0; i<this.SScore; i++) {
        sum += 4;
      }
      for(let i=0; i<this.PScore; i++) {
        sum += 3;
      }
      for(let i=0; i<this.MScore; i++) {
        sum += 2;
      }
      let avg = sum / cnt;
      this.setInfraScore(avg.toFixed(1));
    },
  },
  methods: {
    ...mapActions(houseStore, ["resetHouse", "setInfraScore"]),
    closeSidebar() {
      // console.log("clear");
      this.resetHouse();
    },
    options: {
      legend: { display: false },
      title: {
        display: true,
        text: 'Predicted world population (millions) in 2050'
      }
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
  overflow-y: auto;
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

/* #1- Larger numbers */
ol.large-numbers {
	counter-reset:li; /* Initiate a counter */
	margin-left:0; /* Remove the default left margin */
	padding-left:0; /* Remove the default left padding */
	line-height: 1.25;
  color: #1D0D0C;
}
ol.large-numbers > li {
	position:relative; /* Create a positioning context */
	list-style:none; /* Disable the normal item numbering */
	margin: 1rem 0 1rem 2rem;
	padding: 0 0 0 1rem;
}
ol.large-numbers > li:before {
	content:counter(li); /* Use the counter as content */
	counter-increment:li; /* Increment the counter by 1 */
	/* Position and style the number */
	position:absolute;
	top: -0.3em; /* move numbers up or down as needed */
	left:-0.9em;
	width: 1em;
	text-align:center;
	-moz-box-sizing:border-box;
	-webkit-box-sizing:border-box;
	box-sizing:border-box;
	font-size: 2em;
	font-weight: bold;
	font-weight: 700;
	color: #A8CABA;
  text-shadow:
       3px 3px 0 #838689,
     -1px -1px 0 #838689,  
      1px -1px 0 #838689,
      -1px 1px 0 #838689,
       1px 1px 0 #838689;
}
ol.large-numbers li:hover:before {
	color: #EBE3AA;
}
</style>
