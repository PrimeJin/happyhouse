<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <br/>
          <br/>
          <br/>
          <br/>
          <h1>글 작성</h1>
        </b-col>
      </b-row>
      <qna-input-item type="register" />
    </b-container>
  </template>
  
  <script>
  import QnaInputItem from "@/components/qna/item/QnaInputItem";
  
  export default {
    name: "QnaWrite",
    components: {
      QnaInputItem,
    },
  };
  </script>
  
  <style></style>
  