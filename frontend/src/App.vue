<template>
  <div class="wrapper">
<HeaderComp />
<body>
<main>
  <router-view />
</main>
</body>
<FooterComp />
  </div>
</template>


<script>

import { defineComponent } from 'vue';
import HeaderComp from './components/HeaderComp.vue';
import FooterComp from './components/FooterComp.vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
export default defineComponent({
  components:{
    HeaderComp,
    FooterComp,
  }
})

</script>
<style scoped>
html,body{
   height:100%;
}
.wrapper{
  min-height: 100%;
  display: flex;
  flex-direction: column;
}
main{
  flex: 1 1 auto;

}

</style>
