<template>
  <Waypoint @change="onChange">
    <div class="sidebar-box ftco-animate" :class="animateClass">
      <h3>Tag Cloud</h3>
      <ul class="tagcloud">
        <a href="#" class="tag-cloud-link">dish</a>
        <a href="#" class="tag-cloud-link">menu</a>
        <a href="#" class="tag-cloud-link">food</a>
        <a href="#" class="tag-cloud-link">sweet</a>
        <a href="#" class="tag-cloud-link">tasty</a>
        <a href="#" class="tag-cloud-link">delicious</a>
        <a href="#" class="tag-cloud-link">desserts</a>
        <a href="#" class="tag-cloud-link">drinks</a>
      </ul>
    </div>
  </Waypoint>
</template>
<script lang="ts">
import {defineComponent, ref} from "vue";
import {Waypoint} from "vue-waypoint";

export default defineComponent({
  components: {
    Waypoint
  },setup(){
    const animateClass: any = ref([]);
    const onChange = async (waypointState: any) => {
      waypointState.going === "IN" ? animateClass.value = ['ftco-animated', 'fadeInUp'] : animateClass.value = [];
    }

    return {onChange, animateClass};
  }
})
</script>