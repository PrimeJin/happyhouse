<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
      </b-row>
    </b-container>
  </template>
  
  <script>
  import { deleteUser } from "@/api/member";
  import { mapActions } from "vuex";

  export default {
    methods: {
      ...mapActions('memberStore', ["userLogout"]),
    },

    name: "UserDelete",

    created() {
      let param = this.$route.params.userid;
      console.log(param);
      
      this.userLogout(param);

      deleteUser(
        param,
        ({ data }) => {
          console.log("=====", data);
          let msg = "탈퇴 처리시 문제가 발생했습니다.";
          if (data.message === "success") {
            msg = "탈퇴가 완료되었습니다.";
            // sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
            // sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "main" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
  };
  </script>
  
  <style></style>
  