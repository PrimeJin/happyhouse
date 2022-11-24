<template>
  <b-container class="bv-example-row">
    <b-row class="text-center">
      <b-col>
        <b-input-group prepend="할일" class="mt-3">
          <b-form-input v-model.trim="checklistTitle" @keypress.enter="appendChecklist"></b-form-input>
            <b-button @click="appendChecklist">등록</b-button>
        </b-input-group>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions } from "vuex";

const checklistStore = "checklistStore";

export default {
  name: "ChecklistInput",
  data() {
    return {
      checklistTitle: "",
    };
  },
  methods: {
    ...mapActions(checklistStore, ["createChecklist"]),
    appendChecklist() {
      const checklistItem = {
        title: this.checklistTitle,
        completed: false,
      };
      if (checklistItem.title) this.createChecklist(checklistItem);
      this.checklistTitle = "";
    },
  },
};
</script>

<style scoped>
button {
    color: #FCFAF1;
    background-color: #5F403B;
    border-block-color: #5F403B;
    width: 10%;
}  
</style>
