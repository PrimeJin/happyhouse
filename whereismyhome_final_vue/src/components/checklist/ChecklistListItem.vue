<template>
  <b-container class="bv-example-row">
    <b-row class="mt-3">
      <b-col cols="11" class="p-2 pl-5 text-left" id="checklist">
        <span :class="{ completed: checklist.completed }" @click="modifyChecklistStatus">{{ checklist.title }}</span>
      </b-col>
      <b-col class="p-2" v-if="checklist.completed"><input type="checkbox" @click="modifyChecklistStatus" style="width:30px; height:30px" checked></b-col>
      <b-col class="p-2" v-else><input type="checkbox" @click="modifyChecklistStatus" style="width:30px; height:30px"></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions } from "vuex";

const checklistStore = "checklistStore";

export default {
  name: "ChecklistListItem",
  props: {
    checklist: Object,
  },
  methods: {
    ...mapActions(checklistStore, ["updateChecklistStatus"]),
    modifyChecklistStatus() {
      // this.$store.dispatch("updateChecklistStatus", this.checklist);
      this.updateChecklistStatus(this.checklist);
    },
  },
};
</script>

<style scoped>
#checklist {
  background-color: #C9B0A8;
  color: black;
  font-size: 20;
}

.completed {
  text-decoration: line-through;
  font-style: italic;
  font-weight: bold;
}
</style>
