<g:if test="${flash.success}">
    <div class="bg-success rounded text-center"><p class="text-white">${flash.success}</p></div>
</g:if>
<g:if test="${flash.error}">
    <div class="bg-danger text-center"><p class="text-white">${flash.error}</p></div>
</g:if>