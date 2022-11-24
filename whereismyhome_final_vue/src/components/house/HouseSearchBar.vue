<template>
  <div>
    <b-card no-body style="background-color: #FCFAF1;">
      <b-tabs v-model="tabIndex" card>
        <b-tab title="지역 검색" :title-link-class="linkClass(0)">
          <!-- <h1>지역으로 검색하기</h1> -->
          <b-row class="mt-4 mb-4 text-center" style="border: 0px">
            <b-col class="sm-3">
              <b-form-select
                style="border-radius: 10px; background-color: #ffffff"
                v-model="sidoCode"
                :options="sidos"
                @change="gugunList"
              ></b-form-select>
            </b-col>
            <b-col class="sm-3">
              <b-form-select
                style="border-radius: 10px; background-color: #ffffff"
                v-model="gugunCode"
                :options="guguns"
                @change="dongList"
              ></b-form-select>
            </b-col>
            <b-col class="sm-3">
              <b-form-select
                style="border-radius: 10px; background-color: #ffffff"
                v-model="dongCode"
                :options="dongs"
                @change="searchApt"
              ></b-form-select>
            </b-col>
          </b-row>
        </b-tab>
        <b-tab title="이름 검색" :title-link-class="linkClass(1)">
          <!-- <h1>이름으로 검색하기</h1> -->
          <div class="wrap">
            <div class="search">
              <input
                type="text"
                v-model="keyword"
                @keyup="searchKeywordList"
                class="searchTerm"
                placeholder="검색어를 입력해주세요"
              />
              <button @click="searchAptKeyword" class="searchButton"></button>
            </div>
            <div class="rel_search" v-if="keyword && keywords.length && select != 0">
              <li v-for="(keyword, index) in keywords" :key="index" :house="keyword">
                <ul @click="selectSearchKeyword(keyword)">
                  {{
                    keyword.totalName
                  }}
                </ul>
              </li>
            </div>
          </div>
        </b-tab>
      </b-tabs>
    </b-card>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      tabIndex: 0,
      keyword: null,
      searchKeyword: null,
      select: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses", "keywords"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_APT_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getDong", "getHouseList", "getSearchList"]),
    ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST", "CLEAR_APT_LIST"]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    linkClass(idx) {
      if (this.tabIndex === idx) {
        return ["bg-primary", "text-light"];
      } else {
        return ["bg-light", "text-info"];
      }
    },
    searchApt() {
      if (this.dongCode) this.getHouseList(this.dongCode);
    },
    searchKeywordList() {
      // console.log("키업 호출");
      console.log(this.keyword);
      // console.log(this.keywords);
      if (this.keyword) this.getSearchList(this.keyword);
      this.select = true;
    },
    selectSearchKeyword(searchKeyword) {
      // console.log(searchKeyword);
      this.searchKeyword = searchKeyword.dongCode;
      this.keyword = searchKeyword.totalName;
      this.select = false;
    },
    searchAptKeyword() {
      if (this.searchKeyword) this.getHouseList(this.searchKeyword);
    },
  },
};
</script>

<style scope>
div {
  padding: 0;
  margin: 0;
  top: 0;
  left: 0;

}

.search {
  width: 100%;
  position: relative;
  display: flex;
}

.searchTerm {
  width: 100%;
  border: 0px;
  border-right: none;
  padding: 5%;
  height: 30%;
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #5F403B;
}

.searchTerm:focus {
  color: #5F403B;
}

.searchButton {
  width: 10%;
  height: auto;
  border: 10px;
  background: #5F403B;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
}

.wrap {
  width: 100%;
  top: 50%;
  left: 50%;
}

.rel_search {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  border: 1px solid #5F403B;
  color: #5F403B
}
.rel_search > li > ul {
  text-align: center;
  border: 1px solid #5F403B;
  line-height: 250%;
}
.rel_search > li > ul:hover {
  background-color: #5F403B;
  color: #FCFAF1
}
</style>
