<template>
  <div @click="clickIcon">
    <span
        class="iconify"
        :data-icon="icon"
        :style="{fontSize: `${size}px`, color: color}"
    ></span>
  </div>
</template>
<script lang="ts">
  import {defineComponent, toRefs} from "vue";

  export default defineComponent({
    props: {
      icon: {
        type: String,
        required: true
      },
      size: {
        type: Number,
        required: false,
        default: 14
      },
      color: {
        type: String,
        required: false,
      }
    },
    setup(props, ctx) {
      const clickIcon = () => {
        ctx.emit('clickMethod')
      }
      return {...toRefs(props), clickIcon}
    }
  })
</script>
