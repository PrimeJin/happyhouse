<template>
  <div style="    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;height:100%">

    <b-container class="mt-4" v-if="userInfo"  style="display: flex;" id="mypage">
      
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron style="background-color: #C9B0A8; height: 50vh;display: flex;flex-direction: column;justify-content: center;">
          <h1>회원 정보</h1>
          <div><br/>비밀번호 및 개인정보 수정을 원하시면<br/>정보수정을 눌러주세요.</div>
          
          <hr class="my-4" />
          
          <b-container class="mt-4">
                <table border="1">
                  <tr>
                    <td width="40%">아이디</td>
                    <td>{{userInfo.userid}}</td>
                  </tr>
                  <tr>
                    <td width="40%">이름</td>
                    <td>{{userInfo.name}}</td>
                  </tr>
                </table>
            
                </b-container>
                <hr class="my-4">
                <div style="display:flex;gap:1rem;justify-content: center;width:100%">
                  <button type="button" class="btn register_btn w-25" id="modifyButton" @click="moveModifyPage">정보수정</button>
                  <button type="button" class="btn register_btn w-25" id="deleteButton" @click="deleteUser">회원탈퇴</button>
                </div>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
      
    </b-container>
  </div>
  </template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";
export default {
  name: "UserMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  
  methods: {
    deleteUser() {
      if (confirm("정말로 탈퇴하시겠습니까?")) {
        this.$router.replace({
          name: "deleteUser",
          params: {userid: this.userInfo.userid},
        });
      }
    },

    moveModifyPage() {
      this.$router.push({ name: "modifyUser" });
    },
  },
};
</script>

<style scoped>
h1 {
  color: #FCFAF1;
}

table {
  width: 20vw;
  height: 8vh;
  color: black;
  border-color: #FCFAF1;
  margin: auto;
}

#mypage {
  color: black;
}

#modifyButton{
  color: #FCFAF1;
  background-color: #5F403B;
  border-block-color: #5F403B;
}
#deleteButton{
  color: #FCFAF1;
  background-color: #5F403B;
  border-block-color: #5F403B;
}
</style>
