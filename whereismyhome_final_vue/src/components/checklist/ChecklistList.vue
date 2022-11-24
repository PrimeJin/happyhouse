<template>
  <b-container class="bv-example-row">
    <b-row class="mt-4 mb-4 text-center">
      <b-col>
        <checklist-list-item v-for="(checklist, index) in checklists" :key="index" :checklist="checklist" />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import ChecklistListItem from "@/components/checklist/ChecklistListItem";

const checklistStore = "checklistStore";

export default {
  name: "ChecklistList",
  components: {
    ChecklistListItem,
  },
  computed: {
    ...mapState(checklistStore, ["checklists"]),
  },
};
</script>

<style></style>
