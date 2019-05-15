package com.example.realmbueno;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class RealMigrations implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        final RealmSchema schema = realm.getSchema();

        if (oldVersion == 1) {
            final RealmObjectSchema userSchema = schema.get("Persona");
            userSchema.addField("genero", String.class);
            oldVersion++;
        }

        if (oldVersion == 2){
            final RealmObjectSchema personaSchema = schema.get("Persona");
            personaSchema
                    .addField("fullName", String.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            obj.set("fullName", obj.getString("apellido") + ", " + obj.getString("nombre"));
                        }
                    })
                    .removeField("nombre")
                    .removeField("apellido");
            oldVersion++;
        }
    }
}
