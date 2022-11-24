<template>
  <div style="    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;height:80%">
    <b-container class="mt-3" v-if="userInfo" >
      <b-row>
      </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-card class="text-center mt-3" style= "max-width: 50rem; height: 40vh;" align="left">
            <h2>정보 수정</h2>
            <br/>
            <br/>
            <b-form class="text-left">
              <b-form-group label="아이디:" label-for="userid">
                <b-form-input
                  id="userid"
                  v-model="userid"
                  readonly
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
              <br/>
              <br/>
              <button type="button" class="btn register_btn w-100" @click="modify">수정</button>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
</template>
  
  <script>
  import { mapState } from "vuex";

  const memberStore = "memberStore";
  export default {
    name: "UserModify",
    data() {
      return {
        userid: "",
        password: "",
        name: "",
      };
    },

    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    created() {
        this.userid = this.userInfo.userid;
        this.password = this.userInfo.password;
        this.name = this.userInfo.name;
    },
    methods: {
      modify() {
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
  
        this.$store.dispatch("memberStore/modifyUser", user);
      },
    },
  };
  </script>
  
<style scoped>
  button{
  color: #FCFAF1;
  background-color: #5F403B;
  border-block-color: #5F403B;
  }  
</style>
  