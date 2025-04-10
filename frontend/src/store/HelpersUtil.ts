export default class HelpersUtil {

    static isValidEmail(value: string): boolean {
        var regex = /^[a-z0-9._-]+(\+[a-z0-9._-]+)?@[a-z0-9.-]+\.[a-z]{2,}$/i;
        return regex.test(value);
    }

}