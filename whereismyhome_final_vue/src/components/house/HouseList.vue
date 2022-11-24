<template>
  <b-container v-if="houses && houses.length != 0" fluid="md">
    <house-list-item v-for="(house, index) in houses" :key="index" :house="house" />
  </b-container>
  <b-container v-else fluid="md">
    <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
};
</script>

<style scoped></style>
