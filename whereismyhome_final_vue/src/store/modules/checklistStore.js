const checklistStore = {
  namespaced: true,
  state: {
    checklists: [],
  },
  getters: {
    completedChecklistsCount(state) {
      return state.checklists.filter((checklist) => {
        return checklist.completed === true;
      }).length;
    },
    unCompletedChecklistsCount(state) {
      return state.checklists.filter((checklist) => {
        return checklist.completed === false;
      }).length;
    },
  },
  mutations: {
    CREATE_CHECKLIST(state, checklistItem) {
      state.checklists.push(checklistItem);
    },
    UPDATE_CHECKLIST_STATUS(state, checklistItem) {
      state.checklists = state.checklists.map((checklist) => {
        if (checklist === checklistItem) {
          return {
            ...checklist,
            completed: !checklistItem.completed,
          };
        }
        return checklist;
      });
    },
  },
  actions: {
    // destructuring 활용
    createChecklist({ commit }, todoItem) {
      commit("CREATE_CHECKLIST", todoItem);
    },
    updateChecklistStatus({ commit }, checklistItem) {
      commit("UPDATE_CHECKLIST_STATUS", checklistItem);
    },
  },
};

export default checklistStore;
