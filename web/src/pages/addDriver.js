import RidersClient from '../api/ridersClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class AddDriver extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit','cancleConfirm'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        }

           mount() {
               document.getElementById('create-driver').addEventListener('click', this.submit);
               document.getElementById('cancel').addEventListener('click', this.cancleConfirm);
               this.header.addHeaderToPage();
               this.client = new RidersClient();
           }

           /**
            * Method to run when the add driver submit button is pressed. Call Client to create the
            * new driver.
            */
            async submit(evt) {
                    evt.preventDefault();
                    const errorMessageDisplay = document.getElementById('error-message');
                    errorMessageDisplay.innerText = ``;
                    errorMessageDisplay.classList.add('hidden');
                    const createButton = document.getElementById('create-driver');
                    const origButtonText = createButton.innerText;
                    createButton.innerText = 'Loading...';
                    const currentUser = await this.client.getIdentity();
                    const driverEmail = currentUser.email;
                    const name=currentUser.name;
                    const dateOfBirth = document.getElementById('dateOfBirth').value;
                    const phoneNumber = document.getElementById('phoneNumber').value;
                    const streetAddress = document.getElementById('streetAddress').value;
                    const city = document.getElementById('city').value;
                    const state = document.getElementById('state').value;
                    const zipCode = document.getElementById('zipCode').value;
                    const addedDriver = await this.client.addDriver(driverEmail, name, dateOfBirth, phoneNumber, streetAddress, city, state, zipCode, (error) => {
                    createButton.innerText = "add driver";
                    errorMessageDisplay.innerText = `Error: ${error.message}`;
                    errorMessageDisplay.classList.remove('hidden');
                    });
                    if (addedDriver.driverEmail){
                        const firstLable = document.getElementById('firstLable');
                        firstLable.classList.add('hidden');
                        const successfulMessageDisplay = document.getElementById('successful-message');
                        successfulMessageDisplay.classList.remove('hidden');
                        createButton.classList.add('hidden');
                        document.getElementById('cancel').classList.add('hidden');
                        let timeSecond = 9;
                        successfulMessageDisplay.innerText = `Successfully added:${addedDriver.name} as adriver you will be redirected to driver page 00:${timeSecond} seconds`;
                        const countDown = setInterval(()=>{
                            timeSecond--;
                            successfulMessageDisplay.innerText = `Successfully added:${addedDriver.name} as adriver you will be redirected to driver page 00:${timeSecond} seconds`;
                            if (timeSecond<=0 || timeSecond<1){
                                clearInterval(countDown)
                            }
                        },1000)
                        }
                        const sleep = async (milliseconds) => {
                            await new Promise(counter => {
                                return setTimeout (counter,milliseconds);
                        });
                        };
                        await sleep(9000);
                        window.location.href='driver.html';                   
                }
                
            async cancleConfirm() {
                    window.location.href = `index.html`;
                  }
            }

            /**
             * Main method to run when the page contents have loaded.
             */
            const main = async () => {
                const addDriver = new AddDriver();
                addDriver.mount();
            };

            window.addEventListener('DOMContentLoaded', main);



