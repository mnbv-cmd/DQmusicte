package migration;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema=realm.getSchema();
        if(oldVersion==0)
        {
            /*
             private String musicId;
    private String name;
    private String poster;
    private String path;
     private String author;
             */
            /*
            private String albumId;
    private String name;
    private String poster;
    private String playNum;
    private RealmList<musicmodel> list;
             */
            /*
            album
            hot
             */
            schema.create("musicmodel")
                    .addField("musicId",String.class)
                    .addField("name",String.class)
                    .addField("poster",String.class)
                    .addField("path",String.class)
                    .addField("author",String.class);
            schema.create("albumModel")
                    .addField("albumId",String.class)
                    .addField("name",String.class)
                    .addField("poster",String.class)
                    .addField("playNum",String.class)
                    .addRealmListField("list",schema.get("musicmodel"));
            schema.create("souceModel")
                    .addRealmListField("album",schema.get("albumModel"))
                    .addRealmListField("hot",schema.get("musicmodel"));
            oldVersion=newVersion;
        }
    }
}
