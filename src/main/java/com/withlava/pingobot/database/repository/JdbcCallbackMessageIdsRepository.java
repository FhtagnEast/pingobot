//package com.withlava.pingobot.database.repository;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.List;
//import java.util.function.Function;
//
//public class JdbcCallbackMessageIdsRepository implements CallbackMessageIdsRepository {
//    private final JdbcTemplate jdbcTemplate;
//
//    public JdbcCallbackMessageIdsRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    //TODO:? INSERT ON CONFLICT DO NOTHING
//    public int addUpdateCollectorMessageId(Long notificationId, Integer messageId) {
////        if (!messageIdAlreadyExists(notificationId, messageId, this::getAllUpdateCollectorMessageId)) {
//
////        }
//        return 0;
//    }
//
//    @Override
//    public int addEditMessageId(Long notificationId, Integer messageId) {
////        String sqlRequest
//
//        return 0;
//    }
//
//    @Override
//    public List<Integer> getAllUpdateCollectorMessageId(Long notificationId) {
//        return null;
//    }
//
//    @Override
//    public List<Integer> getAllEditMessageId(Long notificationId) {
//        return null;
//    }
//
//    @Override
//    public Long getNotificationIdByUpdateCollectorMessageId(Integer updateCollectorMessageId) {
//        return null;
//    }
//
//    @Override
//    public Long getNotificationIdByEditMessageId(Integer editMessageId) {
//        return null;
//    }
//
//    private boolean messageIdAlreadyExists(
//            Long notificationId,
//            Integer messageId,
//            Function<Long, List<Integer>> existedIdsProvider)
//    {
//        List<Integer> existedIds = existedIdsProvider.apply(notificationId);
//        return existedIds.contains(messageId);
//    }
//}
