<template>
<body style="height:100%">
  <section class="login_section">
    <b-col cols="1.5"></b-col>
    <b-col cols="9"><div class="w-100 container-fluid outer_container accounts_container">
      <div class="row h-100">
        <div class="col col-sm-12 col-md-12 col-lg-8 m-0 p-0 w-100 h-100 accounts_col">
          <div class="accounts_image w-100 h-100">
            <img src="https://img.freepik.com/free-photo/living-room-interior-wall-mockup-warm-tones-with-leather-sofa-which-is-kitchen-3d-rendering_41470-3753.jpg?w=996&t=st=1669255934~exp=1669256534~hmac=b621cc2084c6e310f0a545aafc205a52ae49485d6ae68996e7c6f7edbfd8037a" alt="accounts_image" class="img-fluid w-100 h-100" />
          </div>
          <!--accounts_image-->
        </div>
        <!--account_col-->
        <div class="col col-sm-12 col-md-12 col-lg-4 m-0 p-0 accounts_col">
          
          <!--회원가입-->
          <div class="accounts_forms signup_form w-100 h-100" style="margin-top:10rem" id="signup">
            <div class="title mt-4 p-4 w-100">
              <h1>회원가입</h1>
              <p class="mt-3" style="text-align: center;">집바구니의 회원이 아니신가요?<br/> 회원가입 후 집바구니를 이용하세요! </p>
            </div>
            <!--title-->
            <form method="post" name="form" class="form w-100 p-4" id="form">
              <div class="row">
              </div>
              <!--form-row-->
              <div class="form-group">
                <label for="userid"></label>
                <input type="text" v-model="userid" class="form-control" id="userid" @keyup.enter="confirm" placeholder="아이디" required />
              </div>
              <div class="form-group">
                <label for="password"></label>
                <input type="password" v-model="password" class="form-control" id="password" @keyup.enter="confirm" placeholder="비밀번호" required />
              </div>
              <div class="form-group">
                <label for="name"></label>
                <input type="text" v-model="name" class="form-control" id="name" @keyup.enter="confirm" placeholder="이름" required />
              </div>
              <div class="form-group">
                <button type="button" class="btn register_btn w-100" @click="regist">회원가입</button>
              </div>
            </form>
            <div class="already_member_box">
              <br/>
              <br/>
              <br/>
              <br/>
              <p class="text-center" @click="movePage">이미 회원이신가요?</p>
            </div>
          </div>
          <!--accounts_forms-->
        </div>
        <!--account_col-->
      </div>
      <!--row-->
    </div></b-col>
    <b-col cols="1.5"></b-col>

    
    <!--accounts_container-->
  </section>
  <!--login_section-->


</body>
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

    movePage() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>
<style scoped>
  :root{
    --background-theme:#e6e9f0;
    --text-color:#000;
}

*,html{
    margin:0px;
    padding:0px;
    font-size: 10px;
    color: var(--text-color);
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}

body{
    width:100%;
    background-color:var(--background-theme);
}
h1{
    font-size: 3.2rem;
    text-align: center;
}
p{
  font-size: 1.2rem;  
}
button{
  color: #FCFAF1;
  background-color: #5F403B;
  border-block-color: #5F403B;
}
/* Sections */
.login_section{
    height: 100%;
    width: 100%;
    display: flex;
    align-items:center;
    position: relative;
    justify-content:center;
    background-color: #FCFAF1;
}

.accounts_container{
    height: 70vh;
    width: 80vh;
    border-radius:10px;
    box-shadow: 8px 8px 8px rgba(0,0,0,0.1);
    background-color: #fcfcfc;
    overflow: hidden;
}


.accounts_image img{
    object-fit: cover;
    object-position: center;
    border-top-left-radius: 10px;
    border-bottom-left-radius: 10px;
}
.accounts_col {
  vertical-align: middle;
}
.accounts_col form .form-group{
    position: relative !important;
}
.accounts_col form .form-control{
    font-size:1.2rem;
    padding:20px 10px;
    margin-bottom:20px;
    background:transparent !important;
}
.accounts_col form label,
.accounts_col form .form-group i{
    position:absolute;
    z-index: 1;
    pointer-events: none;
    top: 50%;
    transform: translateY(-50%);
    font-size:1.2rem;
    left:10px;

}
.accounts_col form label{
    transition: .2s linear;
}
.change_label{
    background-color:#fcfcfc;
    transition: .2s linear;
    transform: scale(0.8);
    top:0 !important;
}
.accounts_col form .form-group i{
    left:90% !important;
    cursor: pointer;
    pointer-events:visible;
}
.register_btn{
    padding:10px 0!important;
    font-size: 1.6rem !important;
    font-weight: 600 !important;

}

.already_member_box p,
.already_member_box span{
    cursor: pointer;
}
/* .login_form, */
</style>
