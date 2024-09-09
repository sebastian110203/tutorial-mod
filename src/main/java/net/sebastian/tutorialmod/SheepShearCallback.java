//package net.sebastian.tutorialmod;
//
//public interface SheepShearCallback {
//
//    Event<SheepShearCallback> EVENT = EventFactory.createArrayBacked(SheepShearCallback.class,
//            (listeners) -> (player, sheep) -> {
//                for (SheepShearCallback listener : listeners) {
//                    ActionResult result = listener.interact(player, sheep);
//
//                    if(result != ActionResult.PASS) {
//                        return result;
//                    }
//                }
//
//                return ActionResult.PASS;
//            });
//
//    ActionResult interact(PlayerEntity player, SheepEntity sheep);
//}
