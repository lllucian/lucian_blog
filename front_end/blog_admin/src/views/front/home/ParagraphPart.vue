<template>
  <Waypoint @change="onChange">
    <div class="sidebar-box ftco-animate" :class="animateClass">
      <h3>Paragraph</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate
        quod
        mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur similique, inventore eos fugit
        cupiditate numquam!</p>
    </div>
  </Waypoint>
</template>
<script lang="ts">
import {defineComponent, ref} from "vue";
import {Waypoint} from "vue-waypoint";

export default defineComponent({
  components: {
    Waypoint
  }, setup() {
    const animateClass: any = ref([]);
    const onChange = async (waypointState: any) => {
      waypointState.going === "IN" ? animateClass.value = ['ftco-animated', 'fadeInUp'] : animateClass.value = [];
    }

    return {onChange, animateClass};
  }
})
</script>