<template>
  <b-container v-if="houses && houses.length != 0" fluid="md" style="background-color: ##FCFAF1">
    <house-list-item v-for="(house, index) in houses" :key="index" :house="house" />
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
