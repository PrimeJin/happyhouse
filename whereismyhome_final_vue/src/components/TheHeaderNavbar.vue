<template>
  <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #5F403B">
    <div class="container-fluid">
      <div class="collapse" id="navbarNav" style="display: grid;
    /* justify-content: space-around; */
    width: 100%;
    grid-template-columns: 1fr 2fr 1fr;">
        <ul></ul>
        <ul class="navbar-nav mx-auto">
          <li class="nav-item">
            <router-link :to="{ name: 'main' }" class="m-2 nav-link">
              <b-icon icon="house-door" font-scale="3"></b-icon>
              메인
            </router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name: 'qna' }" class="m-2 nav-link">
              <b-icon icon="question-square" font-scale="3"></b-icon>
              QnA
            </router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name: 'main' }">
              <b-img
                style="padding: 1em"
                :src="require('@/assets/logo.png')"
                id="logo"
                class="d-inline-block align-top"
                alt="logo"
              ></b-img>
            </router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name: 'house' }" class="m-2 nav-link">
              <b-icon icon="house-fill" font-scale="3"></b-icon>
              아파트정보
            </router-link>
          </li>
          <li class="nav-item">
            <router-link :to="{ name: 'todo' }" class="m-2 nav-link">
              <b-icon icon="calendar-check" font-scale="3"></b-icon>
              TodoList
            </router-link>
          </li>
        </ul>
        <!-- after login -->
        <b-navbar-nav class="ml-auto" v-if="userInfo" style="border-top: 0px; border-bottom: 0px;">
          <b-nav-item class="align-self-center">
            <b-avatar variant="primary" v-text="userInfo.userid.charAt(0).toUpperCase()"></b-avatar>
            {{ userInfo.name }}({{ userInfo.userid }})님 환영합니다.
          </b-nav-item>
          <b-nav-item class="align-self-center">
            <router-link :to="{ name: 'mypage' }" class="link align-self-center">내정보보기</router-link>
          </b-nav-item>
          <b-nav-item class="align-self-center link" @click.prevent="onClickLogout">로그아웃</b-nav-item>
        </b-navbar-nav>
        <!-- before login -->
        <b-navbar-nav class="ml-auto" v-else style="border-top: 0px; border-bottom: 0px">
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'signUp' }" class="link">
                <b-icon icon="person-circle"></b-icon> 회원가입
              </router-link>
            </b-dropdown-item>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'login' }" class="link"> <b-icon icon="key"></b-icon> 로그인 </router-link>
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      console.log(this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped>
#logo {
  width: 150px;
}

@media screen and (min-width: 768px) {
  .navbar .container-fluid {
    flex-direction: column;
  }
  .navbar .navbar-nav .nav-item {
    padding: 0.5em 1em;
    display: flex;
  }
  .navbar-nav {
    border-bottom: 2px solid #fff;
  }
}

.navbar .navbar-nav .nav-link {
  color: rgb(255, 255, 255);
  font-size: 1.1em;
}
.navbar .navbar-nav {
  border-top: 2px solid rgb(255, 255, 255);
  margin-top: 0.5em;
}
</style>
