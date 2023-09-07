import MusicPlaylistClient from '../api/ridersClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class AddTrip extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount','submit','cancleConfirm'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        }

           mount() {
               document.getElementById('create-trip').addEventListener('click', this.submit);
               document.getElementById('cancel').addEventListener('click', this.cancleConfirm);
               this.header.addHeaderToPage();
               this.client = new MusicPlaylistClient();
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
                    const createButton = document.getElementById('create-trip');
                    const origButtonText = createButton.innerText;
                    createButton.innerText = 'Saving...';
                    const currentUser = await this.client.getIdentity();
                    const uuid = require('uuid');
                    const uniqueId = uuid.v1();
                    const tripId = uniqueId;
                    const memberId = currentUser.email;
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
                    const selected="NO";
                    const driverId="Not Selected";
                    const addedtrip = await this.client.addTrip(tripId, memberId, pickupDateTime, pickupStreetAddress, pickupCity, pickupState, pickupZipCode, dropOffStreetAddress, dropOffCity, dropOffState, dropOffZipCode, mobility,selected,driverId, (error) => {
                    createButton.innerText = "create trip";
                    errorMessageDisplay.innerText = `Error: ${error.message}`;
                    errorMessageDisplay.classList.remove('hidden');
                    });
                    if(addedtrip.tripId){
                    const firstLable = document.getElementById('firstLable');
                    firstLable.classList.add('hidden')
                    const successfulMessageDisplay = document.getElementById('successful-message');
                    successfulMessageDisplay.innerText = `successfully added the trip the configuration number is :  ${tripId}  please use this number as reference to this trip`;
                    successfulMessageDisplay.classList.remove('hidden');
                    createButton.classList.add('hidden');
                    const cancelButton = document.getElementById('cancel');
                    cancelButton.innerText="Back";
                    window.Location.href='addTrip.html'
                    }
                }
            async cancleConfirm() {
                        window.location.href = `member.html`;
                      return;
                  }

            }

            /**
             * Main method to run when the page contents have loaded.
             */
            const main = async () => {
                const addTrip = new AddTrip();
                addTrip.mount();
            };

            window.addEventListener('DOMContentLoaded', main);



