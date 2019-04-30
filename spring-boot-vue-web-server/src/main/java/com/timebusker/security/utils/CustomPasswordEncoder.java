package com.timebusker.security.utils;

import com.timebusker.utils.MD5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @DESC:CustomPasswordEncoder
 * @author:timebusker
 * @date:2019/4/26
 */

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return MD5Utils.MD5Encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        return encodedPassword.equals(MD5Utils.MD5Encode(charSequence));
    }
}
