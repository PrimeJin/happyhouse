<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="userid"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="password">
              <b-form-input
                type="password"
                id="password"
                v-model="password"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="name">
              <b-form-input
                type="text"
                id="name"
                v-model="name"
                required
                placeholder="이름 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-button type="button" variant="success" class="m-1" @click="regist">회원가입</b-button>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "UserRegister",
  data() {
    return {
      userid: "",
      password: "",
      name: "",
    };
  },
  methods: {
    regist() {
      if (
        this.userid === "" ||
        this.password === "" ||
        this.name === "" 
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }

      let user = {
        userid: this.userid,
        password: this.password,
        name: this.name,
      };

      this.$store.dispatch("memberStore/createUser", user);
    },
  },
};
</script>

<style></style>
