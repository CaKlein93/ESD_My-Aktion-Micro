<template>
  <div id="app">
    <div id="header" v-if="!['donationForm'].includes($route.name)">
      <p>
        Beispielanwendung
        <b>My-Aktion</b> - Vue.js
      </p>
    </div>
    <router-view />

    <div id="footer" v-if="!['donationForm'].includes($route.name)">
      <p>(C) 2019 ihle.verlag AG, OhneLizenz</p>
    </div>
  </div>
</template>

<style lang="scss">
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#footer {
  text-align: center;
}
</style>