<template>
  <div id="AppHouse">
    <house-map />
  </div>
</template>

<script>
import HouseMap from "@/components/house/HouseMap.vue";

export default {
  name: "AppHouse",
  components: {
    HouseMap,
  },
};
</script>

<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 149, 27, 0.3) 30%);
}
</style>
