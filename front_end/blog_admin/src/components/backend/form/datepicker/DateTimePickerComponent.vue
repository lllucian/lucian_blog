<template>
  <el-form-item :label="labelName" :prop="propName">
    <el-date-picker
        v-model="modelValue"
        type="datetimerange"
        :shortcuts="shortcuts"
        range-separator="至"
        start-placeholder="开始时间"
        end-placeholder="结束时间"
        style="width: 100%"
        :default-time="defaultTime"
        @change="changeDatetime"
        v-bind="$attrs"
    >
    </el-date-picker>
  </el-form-item>
</template>
<script lang="ts" setup>

defineProps<{
  labelName: string,
  propName: string,
  modelValue: string | number | Date | (string | number | Date)[] | undefined
}>();

const shortcuts = [
  {
    text: "上周",
    value: () => {
      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
      return [start, end];
    },
  },
  {
    text: "上个月",
    value: () => {
      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
      return [start, end];
    },
  },
  {
    text: "3个月前",
    value: () => {
      const end = new Date();
      const start = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
      return [start, end];
    },
  },
];

const defaultTime = [
  new Date(2000, 1, 1, 12, 0, 0),
  new Date(2000, 2, 1, 23, 59, 59),
];

const emits = defineEmits<{(e: 'update:modelValue', value: string | number | Date | (string | number | Date)[] | undefined):void}>();
const changeDatetime = (value: any) => {
  emits('update:modelValue', value || []);
}
</script>
