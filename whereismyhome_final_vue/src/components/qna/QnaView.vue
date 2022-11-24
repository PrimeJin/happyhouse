<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert show><h3>글보기</h3></b-alert>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-left">
          <b-button variant="outline-primary" @click="moveList">목록</b-button>
        </b-col>
        <b-col class="text-right" v-if="userInfo.userid === qna.userid">
          <b-button variant="outline-info" size="sm" @click="moveModifyQna" class="mr-2">글수정</b-button>
          <b-button variant="outline-danger" size="sm" @click="deleteQna">글삭제</b-button>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col>
          <b-card
          :header-html="`<h3>${qna.qnano}.
            ${qna.qnasubject} </h3><div><h6>${qna.userid}</div><div>${qna.regtime}</h6></div>`"
            class="mb-2"
            border-variant="dark"
            no-body
            >
            <b-card-body class="text-left">
              <div v-html="message"></div>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
      <!--답글 목록-->
    <div v-if="userInfo.role === 'admin' || userInfo.userid === qna.userid">
      <b-row>
        <b-col>
          <b-alert show><h3>답글목록</h3></b-alert>
        </b-col>
      </b-row>
      <b-row class="mb-1" v-for="(item,idx) in qnareplys" :key="idx">
        <b-col>
          <b-card
          :header-html="`<div><h6>${item.userid}</div><div>${item.replydate}</h6></div>`"
            class="mb-2"
            border-variant="dark"
            no-body
            >
            <b-card-body class="text-left">
              <!--
              <div v-html="replymessage"></div>
              -->
              <div v-html="`${item.replycontent}`"></div>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </div>
    <div v-if="userInfo.role === 'admin' || userInfo.userid === qna.userid">
      <!--답글 작성-->
      <b-row class="mb-1">
        <b-col style="text-align: left">
          <b-form>
            <b-form-group id="content-group" label="답글 작성" label-for="replycontent">
              <b-form-textarea
              id="replycontent"
              v-model="qnareply.replycontent"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"
              ></b-form-textarea>
            </b-form-group>
            
            <b-button variant="outline-info" size="sm" @click="registQnaReply" class="mr-2">답글작성</b-button>
          </b-form>
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>
  
  <script>
  // import moment from "moment";
  import { getQna, writeQnaReply, listQnaReply } from "@/api/qna";
  import { mapState } from "vuex";
  
  const memberStore = "memberStore";
  
  export default {
    name: "QnaDetail",
    data() {
      return {
        qna: {},
        qnareply: {
          qnareplyno: 0,
          userid: "",
          replycontent: "",
        },
        qnareplys: [],
      };
    },
    computed: {
      ...mapState(memberStore, ["userInfo"]),
      message() {
        if (this.qna.qnacontent) return this.qna.qnacontent.split("\n").join("<br>");
        return "";
      },
      // replymessage() {
      //   if (this.qnareply.replycontent) return this.qnareply.replycontent.split("\n").join("<br>");
      //   return "";
      // },
    },
    created() {
      console.log(this.userInfo);
      let param = this.$route.params.qnano;
      getQna(
        param,
        ({ data }) => {
          this.qna = data;
        },
        (error) => {
          console.log(error);
        }
      );
      listQnaReply(
        param,
        ({data}) => {
          this.qnareplys = data;
        },
        (error) =>  {
          console.log(error);
        }
      );
    },
    methods: {
      moveModifyQna() {
        this.$router.replace({
          name: "qnamodify",
          params: { qnano: this.qna.qnano },
        });
        //   this.$router.push({ path: `/qna/modify/${this.qna.qnano}` });
      },
      deleteQna() {
        if (confirm("정말로 삭제?")) {
          this.$router.replace({
            name: "qnadelete",
            params: { qnano: this.qna.qnano },
          });
        }
      },
      moveList() {
        this.$router.push({ name: "qnalist" });
      },
      registQnaReply() {
        console.log("이거 호출됨");
        let param = this.$route.params.qnano;
        let replyparam = {
          qnareplyno: this.qnareply.qnareplyno,
          userid: this.userInfo.userid,
          replycontent: this.qnareply.replycontent,
        };
        writeQnaReply(
          param,
          replyparam,
          ({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if(data.message === "success") {
              msg = "등록이 완료됐습니다.";
            }
            alert(msg);
            this.$router.go(this.$router.currentRoute);
          },
          (error) => {
            console.log(error);
          }
        );
      },
    },
    // filters: {
    //   dateFormat(regtime) {
    //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
    //   },
    // },
  };
  </script>
  
  <style scope>
  .tdClass {
    width: 50px;
    text-align: center;
  }
  </style>
  