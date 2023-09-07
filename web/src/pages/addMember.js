import RidersClient from '../api/ridersClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the manageInventory page of the website.
 */

class AddMember extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit','cancleConfirm'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        }

        /**
             * Add the header to the page and load the MusicPlaylistClient.
             */

           mount() {
               document.getElementById('create-member').addEventListener('click', this.submit);
               document.getElementById('cancel').addEventListener('click', this.cancleConfirm);
               this.header.addHeaderToPage();

               this.client = new RidersClient();
           }

           /**
            * Method to run when the add member submit button is pressed. Call Client to create the
            * new member.
            */
             async submit(evt) {
                    evt.preventDefault();
                    const errorMessageDisplay = document.getElementById('error-message');
                    errorMessageDisplay.innerText = ``;
                    errorMessageDisplay.classList.add('hidden');
                    const createButton = document.getElementById('create-member');
                    const origButtonText = createButton.innerText;
                    createButton.innerText = 'Loading...';
                    const currentUser = await this.client.getIdentity();
                    const memberEmail = currentUser.email;
                    const name = currentUser.name;
                    const dateOfBirth = document.getElementById('dateOfBirth').value;
                    const phoneNumber = document.getElementById('phoneNumber').value;
                    const streetAddress = document.getElementById('streetAddress').value;
                    const city = document.getElementById('city').value;
                    const state = document.getElementById('state').value;
                    const zipCode = document.getElementById('zipCode').value;
                    const addedMember = await this.client.addMember(memberEmail, name, dateOfBirth, phoneNumber, streetAddress, city, state, zipCode, (error) => {
                    createButton.innerText = "create member";
                    errorMessageDisplay.innerText = `Error: ${error.message}`;
                    errorMessageDisplay.classList.remove('hidden');
                    });
                    if (addedMember.memberEmail){
                        const firstLable = document.getElementById('firstLable');
                        firstLable.classList.add('hidden');
                        const successfulMessageDisplay = document.getElementById('successful-message');
                        successfulMessageDisplay.classList.remove('hidden');
                        createButton.classList.add('hidden');
                        document.getElementById('cancel').classList.add('hidden');
                        let timeSecond = 9;
                        successfulMessageDisplay.innerText = `Successfully added:${addedMember.name} as a member you will be redirected to member page in 00:${timeSecond} seconds`;
                        const countDown = setInterval(()=>{
                            timeSecond--;
                            successfulMessageDisplay.innerText = `Successfully added:${addedMember.name} as a member you will be redirected to member page in 00:${timeSecond} seconds`;
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
                        window.location.href='member.html';
                }

            async cancleConfirm() {
                        window.location.href = `index.html`;
                  }
            }

            /**
             * Main method to run when the page contents have loaded.
             */
            const main = async () => {
                const addMember = new AddMember();
                addMember.mount();
            };

            window.addEventListener('DOMContentLoaded', main);



