<template>
  <div v-bind:style="style">
    <h2>Donate Money</h2>
    <b-container>
      <b-row class="my-1">
        <b-col sm="2">
          <label for="donorName">Name:</label>
        </b-col>
        <b-col sm="4">
          <b-form-input
            id="donorName"
            type="text"
            placeholder="Enter your name"
            v-model="donation.donorName"
          ></b-form-input>
        </b-col>
      </b-row>

      <b-row class="my-1">
        <b-col sm="2">
          <label for="iban">IBAN:</label>
        </b-col>
        <b-col sm="4">
          <b-form-input
            id="iban"
            type="text"
            placeholder="Enter your IBAN"
            v-model="donation.account.iban"
          ></b-form-input>
        </b-col>
      </b-row>

      <b-row class="my-1">
        <b-col sm="2">
          <label for="nameOfBank">Name of Bank:</label>
        </b-col>
        <b-col sm="4">
          <b-form-input
            id="nameOfBank"
            type="text"
            placeholder="Enter your banks name"
            v-model="donation.account.nameOfBank"
          ></b-form-input>
        </b-col>
      </b-row>

      <b-row class="my-1">
        <b-col sm="2">
          <label for="amount">Donation amount:</label>
        </b-col>
        <b-col sm="4">
          <b-form-input
            id="amount"
            type="number"
            placeholder="Enter your donation amount"
            v-model="donation.amount"
          ></b-form-input>
        </b-col>
      </b-row>

      <b-form-checkbox
        id="receiptRequired"
        v-model="donation.receiptRequested"
        name="receiptRequired"
      >Yes, i want a receipt</b-form-checkbox>

      <b-button variant="success" @click="sendDonation">Donate</b-button>
    </b-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      styleObject: {
        color: "red",
        bgColor: "black",
        height: "30"
      },
      status: "false",
      campaignId: "",
      donation: {
        account: {
          iban: "",
          nameOfBank: "",
          name: ""
        },
        amount: "",
        donorName: "",
        receiptRequested: false
      }
    };
  },
  methods: {
    sendDonation() {
      this.donation.account.name = this.donation.donorName;
      fetch(
        "http://localhost:8080/rest/donation/" +
          this.campaignId,
        {
          method: "post",
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.donation)
        }.then(response => {
          console.log(response.status)
          })
      );
    }
  },
  computed: {
    style: function() {
      //get height of Browser window
      if (typeof window.innerWidth == "number") {
        //Non-IE
        //this.styleObject.width = window.innerWidth;
        this.styleObject.height = window.innerHeight;
      } else if (
        document.documentElement &&
        (document.documentElement.clientWidth ||
          document.documentElement.clientHeight)
      ) {
        //IE 6+ in 'standards compliant mode'
        //this.styleObject.width = document.documentElement.clientWidth;
        this.styleObject.height = document.documentElement.clientHeight;
      } else if (
        document.body &&
        (document.body.clientWidth || document.body.clientHeight)
      ) {
        //IE 4 compatible
        //this.styleObject.width = document.body.clientWidth;
        this.styleObject.height = document.body.clientHeight;
      }
      return {
        "background-color": this.styleObject.bgColor,
        color: this.styleObject.color,
        height: this.styleObject.height + "px"
      };
    }
  },
  mounted() {
    this.styleObject.bgColor = "#" + this.$route.query.bgColor;
    this.styleObject.color = "#" + this.$route.query.textColor;
    this.campaignId = this.$route.query.campaignId;
  }
};
</script>

