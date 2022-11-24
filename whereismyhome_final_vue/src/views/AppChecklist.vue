<template>
  <div class="text-center container-fluid" style="height: calc(100vh - 102px);">
    <b-row>
      <b-col>
        <checklist-header></checklist-header>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <checklist-input></checklist-input>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <checklist-list></checklist-list>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import ChecklistHeader from "@/components/checklist/ChecklistHeader.vue";
import ChecklistInput from "@/components/checklist/ChecklistInput.vue";
import ChecklistList from "@/components/checklist/ChecklistList.vue";

export default {
  name: "AppChecklist",
  components: {
    ChecklistHeader,
    ChecklistInput,
    ChecklistList,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
</style>
