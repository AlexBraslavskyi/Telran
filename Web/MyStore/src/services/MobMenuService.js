export default class MobMenuService {

    constructor(flag) {
        this.flag = "none"

    }

    getFlag() {
        let flag = this.flag
        return flag;
    }

    changeFlag(flag) {
        if (flag === "none") {
            flag = 'block';
        } else {
            flag = 'none'
        }
        return flag;
    }

}