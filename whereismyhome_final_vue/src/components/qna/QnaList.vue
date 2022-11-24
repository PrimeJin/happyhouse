<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <!-- <b-alert show><h3>글목록</h3></b-alert> -->
          <br/>
          <br/>
          <br/>
          <br/>
          <h1>글 목록</h1>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-right">
          <b-button type="button" @click="moveWrite()">글쓰기</b-button>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-table striped hover :items="qnas" :fields="fields" @row-clicked="viewQna">
            <template #cell(qnasubject)="data">
              <router-link :to="{ name: 'qnaview', params: { qnano: data.item.qnano } }">
                {{ data.item.qnasubject }}
              </router-link>
            </template>
          </b-table>
        </b-col>
      </b-row>
    </b-container>
  </template>
  
  <script>
  import { listQna } from "@/api/qna";
  
  export default {
    name: "BoardList",
    data() {
      return {
        qnas: [],
        fields: [
          { key: "qnano", label: "글번호", tdClass: "tdClass" },
          { key: "qnasubject", label: "제목", tdClass: "tdSubject" },
          { key: "userid", label: "작성자", tdClass: "tdClass" },
          { key: "regtime", label: "작성일", tdClass: "tdClass" },
          { key: "hit", label: "조회수", tdClass: "tdClass" },
        ],
      };
    },
    created() {
      let param = {
        pg: 1,
        spp: 20,
        key: null,
        word: null,
      };
      listQna(
        param,
        ({ data }) => {
          this.qnas = data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
    methods: {
      moveWrite() {
        this.$router.push({ name: "qnawrite" });
      },
      viewQna(qna) {
        this.$router.push({
          name: "qnaview",
          params: { qnano: qna.qnano },
        });
      },
    },
  };
  </script>
  
  <style scoped>
 button{
  color: #FCFAF1;
  background-color: #5F403B;
  border-block-color: #5F403B;
  width: 10%;
}   
  .tdClass {
    width: 50px;
    text-align: center;
  }
  .tdSubject {
    width: 300px;
    text-align: left;
  }
  </style>
  