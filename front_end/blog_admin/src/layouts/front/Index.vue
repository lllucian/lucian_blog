<template>
  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar" v-scroll="handleScroll" :class="menuClass">
    <div class="container">
      <router-link to="/" class="navbar-brand" :class="brandClass">Lucian</router-link>
      <b-button-group>
        <b-button v-for="(route, index) in frontRoutes" :key="index" class="nav-link" @click="goTo(route.path)" :class="activeClass(route.path)">{{route.meta.menu}}</b-button>
      </b-button-group>
    </div>
  </nav>
  <router-view/>
</template>
<script lang="ts">
import {defineComponent, onMounted, ref} from "vue";
import '/@/assets/style.css'
import {onBeforeRouteUpdate, RouteRecordRaw, useRouter} from "vue-router";
import {FrontRoutes} from "/@/router/routes";
import { router } from "/@/router";
export default defineComponent({
  directives: {
      scroll: {
        mounted(el, binding){
          let f = function (evt: any) {
            if (binding.value(evt, el)) {
              window.removeEventListener('scroll', f)
            }
          }
          window.addEventListener('scroll', f)
        }
      }
  },
  setup() {
    const menu = ref(['主页', '博客', '归档', '时间线', '关于我']);
    const menuClass:any = ref([]);

    const brandClass:any = ref([]);
    onBeforeRouteUpdate((to) => {
      if (to.path === '/home')
        brandClass.value = ['navbar-brand-black'];
      else
        brandClass.value = [];
    });
    const handleScroll = () => {
      const st = window.scrollY;
      if (st > 150) {
        menuClass.value = ['scrolled'];
      }
      if (st < 150) {
        menuClass.value = [];
      }
      if ( st > 350 ) {
        menuClass.value = ['scrolled', 'awake'];
      }
    }

    const activeClass = (route_path: string) => {
      return router.currentRoute.value.path === route_path ? 'active' : ''
    }

    onMounted(() => {
      if (useRouter().currentRoute.value.path === '/home')
        brandClass.value = ['navbar-brand-black'];
      else
        brandClass.value = [];
    })

    const frontRoutes = ref((FrontRoutes[0].children || []).filter(route => route.meta.hiddenMenu !== true));

    const goTo = (route_path: string) => router.push(route_path);

    return {
      menu,
      handleScroll,
      menuClass,
      brandClass,
      frontRoutes,
      activeClass,
      goTo
    }
  }
})
</script>
<style>
body {
  background: #fff !important;
}
.navbar .btn-secondary, .navbar .btn-secondary:hover, .navbar .btn-secondary:active{
  border-color: transparent !important;
  background-color: transparent !important;
}

.dropdown-menu{
  border: unset;
}
</style>