<template>
  <div>
    <h1>My Aktions</h1>
    <b-table small striped hover :fields="fields" :items="campaigns" v-if="loaded">
      <template slot="delete">
        <b-button variant="danger" @click="showWarningMessage">X</b-button>
      </template>
      <template slot="edit" slot-scope="data">
        <router-link :to="{ name: 'editCampaign', params: {campaign: data.item } }">edit campaign</router-link>
      </template>
      <template slot="donations" slot-scope="data">
        <router-link :to="{name: 'listDonations', params: {campaignId: data.item.id, name: data.item.name, account: data.item.account}}">list donations</router-link>
      </template>
      <template slot="formular" slot-scope="data">
        <router-link :to="{name: 'editDonationForm', params: {campaignId: data.item.id}}">formular</router-link>
      </template>
    </b-table>
    <router-link :to="'./editCampaign'">
      <b-button variant="outline-primary">Create new Aktion</b-button>
    </router-link>
  </div>
</template>

<script>

export default {
  data() {
    return {
      fields: [
        {
          key: 'delete',
          label: ''
        },
        'name',
        'targetAmount',
        'amountDonatedSoFar',
        {
          key: 'edit',
          label: ''
        },
        {
          key: 'donations',
          label: ''
        },
        {
          key: 'formular',
          label: ''
        }
      ],
      campaigns: [],
      loaded:false
    }
  },
  methods: {
    showWarningMessage() {
      console.log(this.$route.name)
        this.boxOne = ''
        this.$bvModal.msgBoxConfirm('Are you sure?')
          .then(value => {
            this.boxOne = value
            if (this.boxOne) {
                this.deleteCampaign()
            }
          })
          .catch(err => {
            // An error occurred
          })
    },
    deleteCampaign() {
      fetch(
         'http://localhost:8080/rest/campaign' + '/' + campaignId,
         {
           method: 'delete',
           headers: {
             'Content-Type': 'application/json'
           },
         }
         .then(this.updateList())
       )
    },
    updateList() {
      console.log()
      fetch('http://localhost:8080/rest/organizer/campaign/list', {
        method: "get",
        //mode: 'no-cors'
      }).then(response => {
          return response.json()
        }).then(jsonData => {
          this.campaigns = jsonData
          this.loaded = true
        })
    }
  },
  mounted: function() {
    this.updateList()
  }
}
</script>
