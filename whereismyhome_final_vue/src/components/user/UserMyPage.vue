<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
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
          <hr class="my-4" />
          <b-button variant="primary">
            <router-link :to="{ name: 'modifyUser',}" class="link align-self-center">정보수정</router-link>
          </b-button>
          <!-- <b-button variant="primary" href="#" class="mr-1">정보수정</b-button> -->
          <b-button variant="danger" @click="deleteUser">회원탈퇴</b-button>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
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
  },
};
</script>

<style></style>
