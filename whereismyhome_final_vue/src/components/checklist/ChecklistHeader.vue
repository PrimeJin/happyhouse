<template>
  <b-container class="bv-example-row">
    <b-row class="mt-1 text-center">
      <b-col><br/><br/><br/><br/> <h1>체크리스트</h1> </b-col>
    </b-row>
    <b-row class="mt-1 text-center">
      <b-col>
        <b-alert show variant="danger">점검사항 : {{ unCompletedChecklistsCount }}</b-alert>
      </b-col>
      <b-col>
        <b-alert show variant="success">점검완료 : {{ completedChecklistsCount }}</b-alert>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapGetters } from "vuex";

const checklistStore = "checklistStore";

export default {
  name: "ChecklistHeader",
  computed: {
    ...mapGetters(checklistStore, ["allChecklistsCount", "completedChecklistsCount", "unCompletedChecklistsCount"]),
  },
};
</script>

<style></style>
