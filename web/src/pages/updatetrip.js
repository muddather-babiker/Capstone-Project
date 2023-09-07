import RidersClient from '../api/ridersClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the manageInventory page of the website.
 */

class UpdateTrip extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount','submit','cancleConfirm','clientLoaded'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        }

        async clientLoaded() {
            const urlParams = new URLSearchParams(window.location.search);
            const tripId = urlParams.get('tripId');
            const memberId= urlParams.get('memberId'); 
            console.log(tripId);
            console.log(memberId);
            const currenttrip = await this.client.getTrip(tripId,memberId,console.log);
            document.getElementById('pickupDateTime').value=currenttrip.pickupDateTime;
            document.getElementById('pickupStreetAddress').value=currenttrip.pickupStreetAddress;
            document.getElementById('pickupCity').value=currenttrip.pickupCity;
            document.getElementById('pickupState').value=currenttrip.pickupState;
            document.getElementById('pickupZipCode').value=currenttrip.pickupZipCode;
            document.getElementById('dropOffStreetAddress').value=currenttrip.dropOffStreetAddress
            document.getElementById('dropOffCity').value=currenttrip.dropOffCity;
            document.getElementById('dropOffState').ariaPlaceholder=currenttrip.dropOffState;
            document.getElementById('dropOffZipCode').value=currenttrip.dropOffZipCode;
            document.getElementById('mobility').value=currenttrip.mobility;
        }

        /**
             * Add the header to the page and load the MusicPlaylistClient.
             */

           mount() {
               document.getElementById('update-trip').addEventListener('click', this.submit);
               document.getElementById('cancel').addEventListener('click', this.cancleConfirm);

               this.header.addHeaderToPage();

               this.client = new RidersClient();
               this.clientLoaded();
               const successfulMessageDisplay = document.getElementById('successful-message');
               successfulMessageDisplay.innerText = ``;
               successfulMessageDisplay.classList.add('hidden');
           }

           /**
            * Method to run when the add driver submit button is pressed. Call Client to create the
            * new trip.
            */
            async submit(evt) {
                    evt.preventDefault();
                    const errorMessageDisplay = document.getElementById('error-message1');
                    errorMessageDisplay.innerText = ``;
                    errorMessageDisplay.classList.add('hidden');
                    const createButton = document.getElementById('update-trip');
                    const origButtonText = createButton.innerText;
                    createButton.innerText = 'Saving...';
                    const urlParams = new URLSearchParams(window.location.search);
                    const tripId = urlParams.get('tripId');
                    const memberId= urlParams.get('memberId'); 
                    const pickupDateTime = document.getElementById('pickupDateTime').value;
                    const pickupStreetAddress = document.getElementById('pickupStreetAddress').value;
                    const pickupCity = document.getElementById('pickupCity').value;
                    const pickupState = document.getElementById('pickupState').value;
                    const pickupZipCode = document.getElementById('pickupZipCode').value;
                    const dropOffStreetAddress = document.getElementById('dropOffStreetAddress').value;
                    const dropOffCity = document.getElementById('dropOffCity').value;
                    const dropOffState = document.getElementById('dropOffState').value;
                    const dropOffZipCode = document.getElementById('dropOffZipCode').value;
                    const mobility = document.getElementById('mobility').value;
                    const updatedtrip = await this.client.updateTrip(tripId, memberId, pickupDateTime, pickupStreetAddress, pickupCity, pickupState, pickupZipCode, dropOffStreetAddress, dropOffCity, dropOffState, dropOffZipCode, mobility, (error) => {
                    createButton.innerText = "Update trip";
                    errorMessageDisplay.innerText = `Error: ${error.message}`;
                    errorMessageDisplay.classList.remove('hidden');
                    });
                    console.log("the updated trip is");
                    console.log(updatedtrip);
                    if (updatedtrip.tripId){
                    const firstLable = document.getElementById('firstLable');
                    firstLable.classList.add('hidden')
                    const successfulMessageDisplay = document.getElementById('successful-message');
                    successfulMessageDisplay.innerText = `Successfully updated the trip with the configuration number :  ${updatedtrip.tripId} `;
                    successfulMessageDisplay.classList.remove('hidden');
                    createButton.classList.add('hidden');
                    const cancelButton = document.getElementById('cancel');
                    cancelButton.innerText="Back";
                    }
                }
            async cancleConfirm() {
                        window.location.href = `member.html`;
                  }

            }

            /**
             * Main method to run when the page contents have loaded.
             */
            const main = async () => {
                const updateTrip = new UpdateTrip();
                updateTrip.mount();
            };

            window.addEventListener('DOMContentLoaded', main);



