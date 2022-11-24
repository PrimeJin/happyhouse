<template>
  <div style="    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;height:100%">

    <b-container class="mt-4" v-if="userInfo"  style="display: flex;" >
      
      <b-col></b-col>
      <b-col cols="10">
        <b-jumbotron style="background-color: #C9B0A8; height: 50vh;display: flex;flex-direction: column;justify-content: center;">
          <template #header>My Page</template>
          
          <template #lead> 내 정보 확인페이지입니다. </template>
          
          <hr class="my-4" />
          
          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
                ><b-col cols="4" align-self="start">{{ userInfo.userid }}</b-col>
                <b-col cols="2"></b-col>
              </b-row>
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="2" align-self="end">비밀번호</b-col
                  ><b-col cols="4" align-self="start">{{ userInfo.password }}</b-col>
                  <b-col cols="2"></b-col>
                </b-row>
                <b-row>
                  <b-col cols="2"></b-col>
                  <b-col cols="2" align-self="end">이름</b-col
                    ><b-col cols="4" align-self="start">{{ userInfo.name }}</b-col>
                    <b-col cols="2"></b-col>
                  </b-row>
                </b-container>
                <hr class="my-4">
                <!-- <router-link :to="{ name: 'modifyUser'}" class="link align-self-center">정보수정</router-link> -->
                <!-- <b-button variant="primary" href="#" class="mr-1">정보수정</b-button> -->
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
