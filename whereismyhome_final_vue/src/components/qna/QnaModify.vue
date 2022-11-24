<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <br/>
          <br/>
          <br/>
          <br/>
          <h1>글 수정</h1>
        </b-col>
      </b-row>
      <qna-input-item type="modify" />
    </b-container>
  </template>
  
  <script>
  import QnaInputItem from "@/components/qna/item/QnaInputItem";
  
  export default {
    name: "QnaModify",
    components: {
      QnaInputItem,
    },
  };
  </script>
  
  <style></style>
  