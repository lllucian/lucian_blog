<template>
  <Waypoint @change="onChange">
    <div class="blog-entry ftco-animate" :class="animateClass">
      <router-link :to="`/post/${post.id}`" class="img" :style="{backgroundImage: `url('${post.imageUrl}')`}"></router-link>
<!--      <a href="#" class="img" style="background-image: url('/src/assets/image_1.jpg');"></a>-->
      <div class="text pt-2 mt-5">
        <span class="big">mihoyo GenShin</span>
        <h3 class="mb-4">
          <router-link :to="`/post/${post.id}`">{{post.title}}</router-link>
        </h3>
        <p class="mb-4">{{post.description}}</p>
        <div class="author mb-4 d-flex align-items-center">
          <router-link :to="`/post/${post.id}`" class="img" :style="{backgroundImage: `url('${post.imageUrl}')`}"></router-link>
          <div class="ml-3 info">
            <span>作者</span>
            <h3><a href="#">{{post.username}}</a>, <span>{{post.createdAt}}</span></h3>
          </div>
        </div>
        <div class="meta-wrap d-md-flex align-items-center">
          <div class="half order-md-last text-md-right">
            <p class="meta">
              <span><span class="iconify" data-icon="bi:calendar2-heart-fill" data-flip="horizontal" style="margin-right: 10px"></span>3</span>
              <span><span class="iconify" data-icon="fa-regular:eye" data-flip="horizontal" style="margin-right: 10px"></span>100</span>
              <span><span class="iconify" data-icon="fa:comment" data-flip="horizontal" style="margin-right: 10px"></span>5</span>
            </p>
          </div>
          <div class="half">
            <p>
              <router-link :to="`/post/${post.id}`" class="btn btn-primary p-3 px-xl-4 py-xl-3">继续阅读</router-link>
            </p>
          </div>
        </div>
      </div>
    </div>
  </Waypoint>
</template>
<script lang="ts">
import {defineComponent, ref, toRefs} from "vue";
import { Waypoint } from 'vue-waypoint'

export default defineComponent({
  components: {
    Waypoint
  },
  props: {
    post: {
      type: Object,
      required: true
    }
  },
  setup(props) {
    const {post} = toRefs(props);
    const animateClass:any = ref([]);
    const onChange = async (waypointState:any) => {
      waypointState.going === "IN" ? animateClass.value = ['ftco-animated', 'fadeInUp'] : animateClass.value = [];
    }

    return { onChange, animateClass, post };
  }
})
</script>