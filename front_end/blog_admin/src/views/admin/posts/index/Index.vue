<template>
  <el-row>
    <el-col :span="24">
      <Search></Search>
    </el-col>
  </el-row>
  <el-row>
    <el-col :span="24">
      <DataForm></DataForm>
    </el-col>
  </el-row>
  <el-backtop target=".el-main" />
</template>
<script lang="ts">
import { defineComponent } from "vue";
import Search from "./Search.vue";
import DataForm from "./DataForm.vue";

export default defineComponent({
  components: {
    Search,
    DataForm,
  },
  setup() {},
});
</script>
<style lang="scss" scoped>
    .el-row {
        margin-bottom: 20px;
        &:last-child {
            margin-bottom: 0;
        }
    }
</style>
