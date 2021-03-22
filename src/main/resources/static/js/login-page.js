class UserFormValues {
    #isPassVisible = false;

    isPassVisible() {
        return this.#isPassVisible;
    }

    switchPassValue() {
        this.#isPassVisible = !this.#isPassVisible;
    }
}

const currUser = new UserFormValues();


// let _isPassVisible = false;
//
// function isPassVisible() {
//     return _isPassVisible;
// }
//
// function switchIsPassVisible() {
//     _isPassVisible = !_isPassVisible;
// }
