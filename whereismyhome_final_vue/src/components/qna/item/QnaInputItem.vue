<template>
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form @submit="onSubmit" @reset="onReset">
          <b-form-group id="userid-group" label="작성자:" label-for="userid" description="작성자를 입력하세요.">
            <b-form-input
              id="userid"
              type="text"
              readonly
              :placeholder="userInfo.userid"
            ></b-form-input>
          </b-form-group>
  
          <b-form-group id="subject-group" label="제목:" label-for="qnasubject" description="제목을 입력하세요.">
            <b-form-input
              id="qnasubject"
              v-model="qna.qnasubject"
              type="text"
              required
              placeholder="제목 입력..."
            ></b-form-input>
          </b-form-group>
  
          <b-form-group id="content-group" label="내용:" label-for="qnacontent">
            <b-form-textarea
              id="qnacontent"
              v-model="qna.qnacontent"
              placeholder="내용 입력..."
              rows="10"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>
  
          <b-button type="submit" variant="primary" class="m-1" v-if="this.type === 'register'">글작성</b-button>
          <b-button type="submit" variant="primary" class="m-1" v-else>글수정</b-button>
          <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
        </b-form>
      </b-col>
    </b-row>
  </template>
  
  <script>
  import { writeQna, modifyQna, getQna } from "@/api/qna";
  import { mapState } from "vuex";
  const memberStore = "memberStore";

  export default {
    name: "QnaInputItem",
    data() {
      return {
        qna: {
          qnano: 0,
          userid: "",
          qnasubject: "",
          qnacontent: "",
        },
        isUserid: false,
      };
    },
    props: {
      type: { type: String },
    },

    computed: {
      ...mapState(memberStore, ["userInfo"]),
    },

    created() {
      if (this.type === "modify") {

        let param = this.$route.params.qnano;
        getQna(
          param,
          ({ data }) => {
            // this.qna.qnano = data.qna.qnano;
            // this.qna.userid = data.qna.userid;
            // this.qna.subject = data.qna.subject;
            // this.qna.content = data.qna.content;
            this.qna = data;
          },
          (error) => {
            console.log(error);
          }
        );
        this.isUserid = true;
      }
    },
    methods: {
      onSubmit(event) {
        event.preventDefault();
  
        let err = true;
        let msg = "";
        if(this.qna.userid == '') {
          msg = "작성자 입력해주세요";
        }
        if(this.qna.qnasubject == '') {
          msg = "제목 입력해주세요";
        }
        if(this.qna.qnacontent == '') {
          msg = "내용 입력해주세요";
        }
  
        if (!err) alert(msg);
        else this.type === "register" ? this.registQna() : this.modifyQna();
      },
      onReset(event) {
        event.preventDefault();
        this.qna.qnano = 0;
        this.qna.qnasubject = "";
        this.qna.qnacontent = "";
        this.moveList();
      },
      registQna() {
        let param = {
          userid: this.userInfo.userid,
          qnasubject: this.qna.qnasubject,
          qnacontent: this.qna.qnacontent,
        };
        writeQna(
          param,
          ({ data }) => {
            let msg = "등록 처리시 문제가 발생했습니다.";
            if (data.message === "success") {
              msg = "등록이 완료되었습니다.";
            }
            alert(msg);
            this.moveList();
          },
          (error) => {
            console.log(error);
          }
        );
      },
      modifyQna() {
        let param = {
          qnano: this.qna.qnano,
          userid: this.qna.userid,
          qnasubject: this.qna.qnasubject,
          qnacontent: this.qna.qnacontent,
        };
        modifyQna(
          param,
          ({ data }) => {
            let msg = "수정 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "수정이 완료되었습니다.";
            }
            alert(msg);
            // 현재 route를 /list로 변경.
            this.moveList();
          },
          (error) => {
            console.log(error);
          }
        );
      },
      moveList() {
        this.$router.push({ name: "qnalist" });
      },
    },
  };
  </script>
  
  <style></style>
  