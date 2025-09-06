package com.internintelligence.gmailapplication;

import com.internintelligence.gmailapplication.model.enums.Language;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SimpleDictionaryEngine {
    private final Map<String, String> dict = new HashMap<>();


    public SimpleDictionaryEngine(){
// EN -> AZ
        dict.put(key(Language.EN, Language.AZ, "hello"), "salam");
        dict.put(key(Language.EN, Language.AZ, "good morning"), "sabahınız xeyir");
        dict.put(key(Language.EN, Language.AZ, "thank you"), "təşəkkürlər");
// EN -> TR
        dict.put(key(Language.EN, Language.TR, "hello"), "merhaba");
        dict.put(key(Language.EN, Language.TR, "good morning"), "günaydın");
        dict.put(key(Language.EN, Language.TR, "thank you"), "teşekkürler");
// AZ -> EN
        dict.put(key(Language.AZ, Language.EN, "salam"), "hello");
        dict.put(key(Language.AZ, Language.EN, "sabahınız xeyir"), "good morning");
        dict.put(key(Language.AZ, Language.EN, "təşəkkürlər"), "thank you");
// TR -> EN
        dict.put(key(Language.TR, Language.EN, "merhaba"), "hello");
        dict.put(key(Language.TR, Language.EN, "günaydın"), "good morning");
        dict.put(key(Language.TR, Language.EN, "teşekkürler"), "thank you");
    }


    private String key(Language s, Language t, String text){
        return s+"->"+t+":"+text.toLowerCase().trim();
    }


    public String translate(Language s, Language t, String text){
        if(s == t) return text;
        return dict.getOrDefault(key(s,t,text), text + " ("+t+")");
    }
}
