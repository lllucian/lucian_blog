import { createStore } from "vuex";

const store = createStore({
  state: () => ({
    // user info
    userInfo: null,
    // token
    token: undefined,
    // roleList
    roleList: [],
    // Whether the login expired
    sessionTimeout: false,
    // Last fetch time
    lastUpdateTime: 0,
  }),
  getters: {
    doneTodos: (state) => {
      return state.token;
    },
  },
});
