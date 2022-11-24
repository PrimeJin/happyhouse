<template>
<div class="background">
  <section>
    <div class="services-grid">
      <div class="service service1">
        <i class="ti-bar-chart"></i>
        <h4>모든 걸 한 번에!</h4>
        <p>집을 구할 때 고려해야 하는 다양한 체크리스트들. 이쪽저쪽 정말 불편하셨죠? 집바구니에서는 한 번에 모든 정보를 담아보세요!</p>
        <a href="#" class="cta">체크리스트 보러가기<span class="ti-angle-right"/></a>
      </div>

      <div class="service service2">
        <i class="ti-light-bulb"></i>
        <h4>지금 필요한 바구니</h4>
        <p>주변 상권, 교육기관, 지하철역까지! 생활에 필요한 인프라 요소를 한눈에 알아볼 수 있어요. 다른 장소와 인프라를 비교해보세요.</p>
        <a href="#" class="cta">인프라 보러가기<span class="ti-angle-right"/></a>
      </div>

      <div class="service service3">
        <i class="ti-money"></i>
        <h4>도움이 필요하실 때는</h4>
        <p>상단의 QNA 메뉴를 이용해주세요. 집을 구하는데 어려움이 없도록 담당자가 빠르게 답변해드립니다.</p>
        <a href="#" class="cta">질문하러 가기<span class="ti-angle-right"/></a>
      </div>
    </div>
  </section>
  </div>
</template>

<script>
export default {
  name: "AppMain",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.background{
  height: 90vh;
  overflow: hidden;
  margin:0;
  background-image: url("@/assets/back.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}

.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}

a {
  text-decoration: none;
  margin: 1rem 0;
  font-weight: 600;
  text-transform: uppercase;
  font-size: 0.8125rem;
  letter-spacing: 1px;
}

p {
  font-size: 0.9rem;
  margin: 1rem 0;
  line-height: 1.5;
}

section {
  max-width: 80%;
  min-height: 80%;
  margin: 5% auto;
  text-align: center;
  padding: 5%;
  background-color: #fcfcfc;
  border-radius: 15px;
}

h3 {
  font-family: 'Montserrat', sans-serif;
  font-weight: 600;
  color: #333;
  font-size: 1.825rem;
  margin: 1.3rem 0;
}

.section-lead {
  max-width: 600px;
  margin: 1rem auto 1.5rem;
}

.service a {
  color: #5b7abb;
  display: block;
}

.service h4 {
  font-family: 'Montserrat', sans-serif;
  font-weight: 600;
  color: #56ceba;
  font-size: 1.3rem;
  margin: 1rem 0 0.6rem;
}

.services-grid {
  display: -webkit-box;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
}

.service {
  background: #fff;
  margin: 20px;
  padding: 20px;
  border-radius: 4px;
  text-align: center;
  -webkit-box-flex: 1;
  flex: 1;
  display: -webkit-box;
  display: flex;
  flex-wrap: wrap;
  border: 2px solid #e7e7e7;
  -webkit-transition: all 0.3s ease;
  -moz-transition: all 0.3s ease;
  transition: all 0.3s ease;
}

.service:hover {
  -webkit-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.08);
  -moz-box-shadow: 0 5px 10px rgba(0, 0, 0, 0.08);
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.08);
}

.service i {
  font-size: 3.45rem;
  margin: 1rem 0;
}

.service1 i,
.service1 h4,
.service1 .cta {
  color: #FC7000;
}

.service1:hover {
  border: 2px solid #FC7000;
}

.service2 i,
.service2 h4,
.service2 .cta {
  color: #A44528;
}

.service2:hover {
  border: 2px solid #A44528;
}

.service3 i,
.service3 h4,
.service3 .cta {
  color: #E89B19;
}

.service3:hover {
  border: 2px solid #E89B19;
}

.service .cta span {
  font-size: 0.6rem;
}

.service > * {
  flex: 1 1 100%;
}

.service .cta {
  align-self: flex-end;
}

@media all and (max-width:900px) {
  .services-grid {
    display: -webkit-box;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    flex-direction: column;
  }
}
</style>
